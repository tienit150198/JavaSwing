package pro03;

public class FractionException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2718416007393349864L;

	public FractionException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
