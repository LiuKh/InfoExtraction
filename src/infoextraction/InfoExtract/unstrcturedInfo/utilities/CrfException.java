/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction.InfoExtract.unstrcturedInfo.utilities;

/**
 *
 * @author Kaihua Liu
 */
public class CrfException extends RuntimeException
{
	private static final long serialVersionUID = 5414394233000815286L;

	public CrfException()
	{
		super();
	}

	public CrfException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CrfException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CrfException(String message)
	{
		super(message);
	}

	public CrfException(Throwable cause)
	{
		super(cause);
	}
	

}
