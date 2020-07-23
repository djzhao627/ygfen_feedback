package cn.djzhao.service;

import cn.djzhao.util.PageBean;
import cn.djzhao.util.TableData;
import cn.djzhao.model.Message;

import java.util.List;

/**
 * @Author: laizc
 * @Date: Created in 10:04 2019-06-27
 */
public interface MessageService {
    /**
     * 获取全部数据
     *
     * @return
     */
    List<Message> list();

    /**
     * 获取表单数据
     *
     * @param pageBean
     * @return
     */
    TableData<Message> getTableData(PageBean pageBean);

    /**
     * 获取设备列表
     * @return 设备列表
     */
    List<Message> getMessage();

    /**
     * 提交留言
     * @param message
     */
    void submitMessage(Message message);
}
