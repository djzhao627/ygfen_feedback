package cn.djzhao.service;

import com.github.pagehelper.PageHelper;
import cn.djzhao.dao.MessageDao;
import cn.djzhao.model.Message;
import cn.djzhao.util.PageBean;
import cn.djzhao.util.TableData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: laizc
 * @Date: Created in 10:05 2019-06-27
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageDao messageDao;

    @Override
    public List<Message> list() {
        return messageDao.selectAll();
    }

    @Override
    public TableData<Message> getTableData(PageBean pageBean) {
        int count = messageDao.selectCount(null);
        if (count > 0) {
            PageHelper.startPage((pageBean.getOffset() / pageBean.getLimit()) + 1, pageBean.getLimit());
            Example example = new Example(Message.class);
            if (StringUtils.isNotBlank(pageBean.getOrder())) {
                example.setOrderByClause(pageBean.getSort() + " " + pageBean.getOrder());
            }

            Example.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(pageBean.getSearch())) {
                criteria.orLike("companyName", "%" + pageBean.getSearch() + "%")
                        .orLike("personalName", "%" + pageBean.getSearch() + "%")
                        .orLike("email", "%" + pageBean.getSearch() + "%")
                        .orLike("mobile", "%" + pageBean.getSearch() + "%")
                        .orLike("content", "%" + pageBean.getSearch() + "%");
            }
            example.setOrderByClause("create_time DESC");
            List<Message> list = messageDao.selectByExample(example);
            return TableData.bulid(count, list);
        }
        return TableData.empty();
    }

    @Override
    public List<Message> getMessage() {
        return messageDao.selectAll();
    }

    @Override
    public void submitMessage(Message message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setCreateTime(simpleDateFormat.format(new Date()));
        try {
            messageDao.insertSelective(message);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

}
