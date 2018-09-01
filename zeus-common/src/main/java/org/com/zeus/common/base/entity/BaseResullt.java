package org.com.zeus.common.base.entity;

import lombok.Data;

@Data
public class BaseResullt<Tdata> {
	public Boolean isSuccess;

	public Tdata data;

	public String msg;


	public static class utils {
		public static <T> BaseResullt<T> setSuccess() {
			BaseResullt<T> dataResult = new BaseResullt<T>();
			dataResult.setIsSuccess(true);
			dataResult.setMsg("操作成功");
			return dataResult;
		}

		public static <T> BaseResullt<T> setSuccessResult(T data) {
			BaseResullt<T> dataResult = setSuccess();
			dataResult.setData(data);
			return dataResult;
		}

		public static <T> BaseResullt<T> setFail() {
			BaseResullt<T> dataResult = new BaseResullt<T>();
			dataResult.setIsSuccess(false);
			dataResult.setMsg("操作失败");
			return dataResult;
		}

		public static <T> BaseResullt<T> setFailMsg(String msg) {
			BaseResullt<T> dataResult = setFail();
			dataResult.setMsg(msg);
			return dataResult;
		}
	}

}
