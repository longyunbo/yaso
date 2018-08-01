package com.drag.yaso.pt.resp;

import com.drag.yaso.common.BaseResponse;

import lombok.Data;

@Data
public class PtGoodsResp extends BaseResponse{
	
	private static final long serialVersionUID = -4195525113654121659L;
	
	private int ptgoodsId;
	
	private String ptcode;
}
