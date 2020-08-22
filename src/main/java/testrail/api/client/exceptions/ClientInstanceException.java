/**
 * TestRail API binding for Java (API v2, available since TestRail 3.0)
 *
 * Learn more:
 *
 * http://docs.gurock.com/testrail-api2/start
 * http://docs.gurock.com/testrail-api2/accessing
 *
 * Copyright Gurock Software GmbH. See license.md for details.
 */

package testrail.api.client.exceptions;
 
public class ClientInstanceException extends RuntimeException
{
	public ClientInstanceException(String message)
	{
		super(message);
	}
}
