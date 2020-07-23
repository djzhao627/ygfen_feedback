package cn.djzhao.dao;

import cn.djzhao.model.Message;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: laizc
 * @Date: Created in 10:54 2019-06-27
 */
public interface MessageDao extends Mapper<Message>,IdsMapper<Message> {
}
