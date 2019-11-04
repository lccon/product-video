package cilicili.jz2.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultMsg implements Serializable{

	private static Map<String, Serializable> resultStatus;

	public static Map<String, Serializable> errorContent(){
		resultStatus = new HashMap<>();
		resultStatus.put("status", "failure");
		return resultStatus;
	}

	public static Map<String, Serializable> error(String msg){
		resultStatus = new HashMap<>();
		resultStatus.put("status", "failure");
		resultStatus.put("msg", msg);
		return resultStatus;
	}

	public static Map<String, Serializable> success() {
		resultStatus = new HashMap<>();
		resultStatus.put("status", "success");
		return resultStatus;
	}

}
