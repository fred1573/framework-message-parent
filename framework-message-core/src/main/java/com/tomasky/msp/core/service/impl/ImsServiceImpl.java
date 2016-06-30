package com.tomasky.msp.core.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tomasky.msp.core.dao.impl.ImsMessageDao;
import com.tomasky.msp.core.service.ImsService;
import com.tomasky.msp.model.BuildImsMessageQuery;
import com.tomasky.msp.model.ImsMessage;
import com.tomasky.msp.model.ImsMessageType;
import com.tomasky.msp.model.Page;
import com.tomasky.msp.model.QueryFrom;


@Service
public class ImsServiceImpl implements ImsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ImsServiceImpl.class);

    @Autowired
    protected ImsMessageDao imsMessageDao;

    @Override
    public void setMessageRead(ImsMessage im) {
        im.markMessageRead();
        LOGGER.info(im.getId() + "------标记已读----------" + im.getHasRead());
        this.saveOrUpdate(im);
    }

    @Override
    public void saveOrUpdate(ImsMessage ims) {
        imsMessageDao.saveOrUpdate(ims);
    }

    @Override
    public ImsMessage findOne(String id) {
        return imsMessageDao.findOne(id);
    }

    @Override
    public Page<ImsMessage> pageAll(Page<ImsMessage> page, QueryFrom queryFrom) {
        Query query = new BuildImsMessageQuery.Builder(queryFrom).build().buildQuery().with(new Sort(Sort.Direction.DESC, "sendTime"));
        imsMessageDao.page(page, query);
        return page;
    }

    @Override
    public Page<ImsMessage> pageUnRead(Page<ImsMessage> page,
                                       QueryFrom queryFrom) {
        Query query = new BuildImsMessageQuery.Builder(queryFrom).hasRead(false).valid(true).build().buildQuery();
        imsMessageDao.page(page, query);
        List<ImsMessage> msgs = page.getDatas();
        if (CollectionUtils.isNotEmpty(msgs)) {
            for (int i = msgs.size() - 1; i >= 0; i--) {
                ImsMessage imsMessage = msgs.get(i);
                if (!imsMessage.getValid()) {
                    setMessageRead(imsMessage);
                    msgs.remove(imsMessage);
                }
            }
        }
        return page;
    }

    @Override
    public Page<ImsMessage> pageAndMarkMessageRead(Page<ImsMessage> page, QueryFrom queryFrom) {
        Query query = new BuildImsMessageQuery.Builder(queryFrom).hasRead(false).build().buildQuery();
        imsMessageDao.page(page, query);
        List<ImsMessage> imsMessages = page.getDatas();
        for (int i = imsMessages.size() - 1; i >= 0; i--) {
            ImsMessage imsMessage = imsMessages.get(i);
            if (imsMessage.getValid()) {
                this.setMessageRead(imsMessage);
            } else {
                if (imsMessage.getImsMessageType().equals(ImsMessageType.EXPIRE_MESSAGE)) {
                    this.setMessageRead(imsMessage);
                }
                imsMessages.remove(imsMessage);
            }
        }
        return page;
    }


    @Override
    public List<ImsMessage> findNotMarkAlertMessage(String orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderId").is(orderId));
        query.addCriteria(Criteria.where("imsMessageType").is(ImsMessageType.ALERT_MESSAGE));
        query.addCriteria(Criteria.where("hasRead").is(false));
        return imsMessageDao.find(query);
    }


    @Override
    public Long countUnreadMessage(QueryFrom queryFrom) {
        Query query = new BuildImsMessageQuery.Builder(queryFrom).hasRead(false).build().buildQuery();
        return imsMessageDao.count(query);
    }

    @Override
    public long jsonFormatRepair() {
        Query query = new Query();
        query.addCriteria(Criteria.where("channelId").is(1));
        query.addCriteria(Criteria.where("imsType").is("deal_oms_order_result"));
        query.addCriteria(Criteria.where("messageType").is("IMS"));
        query.addCriteria(Criteria.where("hasRead").is(false));
        List<ImsMessage> imsMessages = imsMessageDao.find(query);
        int count = 0;
        if (CollectionUtils.isNotEmpty(imsMessages)) {
            for (ImsMessage imsMessage : imsMessages) {
                if(!(imsMessage.getData() instanceof String)){
                    imsMessage.setData(JSON.toJSONString(imsMessage.getData()));
                    imsMessageDao.saveOrUpdate(imsMessage);
                    count++;
                }
            }
        }
        return count;
    }
}
