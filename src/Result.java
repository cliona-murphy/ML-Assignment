
public class Result {
	private boolean result;
	private String predictedRes;
	private String actualRes;
	
	public Result(boolean result, String predicted, String actual){
		this.result = result;
		predictedRes = predicted;
		actualRes = actual;
	}

	//Getters and Setters
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getPredicted() {
		return predictedRes;
	}

	public void setPredicted(String predicted) {
		predictedRes = predicted;
	}

	public String getActual() {
		return actualRes;
	}

	public void setActual(String actual) {
		actualRes = actual;
	}
	
}
