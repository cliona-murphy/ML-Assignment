
public class Result {
	private boolean result;
	private String predictedRes;
	private String actualRes;
	
	public Result(boolean result, String predicted, String actual){
		this.result = result;
		predictedRes = predicted;
		actualRes = actual;
	}
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getPredictedResult() {
		return predictedRes;
	}

	public void setPredictedResult(String predicted) {
		predictedRes = predicted;
	}

	public String getActualResult() {
		return actualRes;
	}

	public void setActualResult(String actual) {
		actualRes = actual;
	}
	
}
