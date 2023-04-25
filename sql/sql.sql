create database sakeray_authorization;
CREATE TABLE sakeray_authorization.`user_account`
(
    `id`               bigint(20) unsigned NOT NULL COMMENT '主键',
    `user_name`        varchar(50)         NOT NULL DEFAULT '' COMMENT '姓名',
    `user_mobile`      varchar(50)         NOT NULL DEFAULT '' COMMENT '手机号',
    `user_account`     varchar(50)         NOT NULL COMMENT '账号',
    `user_password`    varchar(500)        NOT NULL COMMENT '密码',
    `status`           tinyint(4)          NOT NULL DEFAULT 1 COMMENT '状态1：启用，2禁用',
    `create_user_id`   varchar(32)         NOT NULL DEFAULT '' COMMENT '创建人id',
    `create_user_name` varchar(32)         NOT NULL DEFAULT '' COMMENT '创建人',
    `create_time`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user_id`   varchar(32)         NOT NULL DEFAULT '' COMMENT '更新人id',
    `update_user_name` varchar(32)         NOT NULL DEFAULT '' COMMENT '更新人',
    `update_time`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uq_useraccount` (`user_account`),
    KEY `idx_username` (`user_name`(10)),
    KEY `idx_usermobile` (`user_mobile`(11))
) COMMENT ='用户账号密码表';