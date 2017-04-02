CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL DEFAULT '0',
  `time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `data_per_second` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` bigint(20) NOT NULL DEFAULT '0',
  `max_value` double NOT NULL DEFAULT '0',
  `min_value` double NOT NULL DEFAULT '0',
  `sum` double NOT NULL DEFAULT '0',
  `time_in_seconds` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;