create table todo_items (
	`id` int primary key auto_increment comment 'ID',
	`expiration_date` date not null comment '期限日',
	`todo_item` varchar(50) not null comment 'TODO項目',
	`is_completed` tinyint not null default 0 comment '完了フラグ',
	`is_deleted` tinyint not null default 0 comment '削除フラグ',
	`create_date_time` datetime not null default current_timestamp comment '作成日時',
	`update_date_time` datetime not null default current_timestamp on update current_timestamp comment '更新日時'
)  ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = 'TODOリスト';