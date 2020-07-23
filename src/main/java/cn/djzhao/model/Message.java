package cn.djzhao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class Message {
    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private Integer id;

    // 公司名称
    private String companyName;

    // 个人名称
    private String personalName;

    // 手机
    private String mobile;

    // 邮箱
    private String email;

    // 内容
    private String content;

    // 时间
    private String createTime;

}
