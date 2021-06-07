package com.springboot.cloud.pojo;

import lombok.Data;

/**
 * @author : lirisheng
 * @date : 2020/9/15
 **/
@Data
public class StockOrder   {


	private Long id;

	private Long sid;

	private String name;

	private java.util.Date createTime;

}
