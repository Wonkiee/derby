/*
 
   Derby - Class org.apache.derby.jdbc.EmbeddedXADataSource40
 
   Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
 */

package org.apache.derby.jdbc;

import java.sql.BaseQuery;
import java.sql.QueryObjectFactory;
import java.sql.QueryObjectGenerator;
import org.apache.derby.iapi.jdbc.ResourceAdapter;

import java.sql.SQLException;
import javax.sql.XAConnection;
import javax.sql.XADataSource;

/**

	EmbeddedXADataSource40 is Derby's XADataSource implementation for JDBC4.0.
	

	<P>An XADataSource is a factory for XAConnection objects.  It represents a
	RM in a DTP environment.  An object that implements the XADataSource
	interface is typically registered with a JNDI service provider.   	
	<P>
	EmbeddedXADataSource40 supports the JDBC 4.0 specification
	for the J2SE 6.0 Java Virtual Machine environment. Use EmbeddedXADataSource
	if your application runs in the following environments:
	<UL>
	<LI> JDBC 3.0 - Java 2 - JDK 1.4, J2SE 5.0
	<LI> JDBC 2.0 - Java 2 - JDK 1.2,1.3
	</UL>

	<P>EmbeddedXADataSource40 object only works on a local database.  There is no
	client/server support.  An EmbeddedXADataSource40 object must live in the same jvm as
	the database. 

	<P>EmbeddedXADataSource40 is serializable and referenceable.

	<P>See EmbeddedDataSource40 for DataSource properties.

 */
public class EmbeddedXADataSource40 extends EmbeddedXADataSource {
    /** Creates a new instance of EmbeddedXADataSource40 */
    public EmbeddedXADataSource40() {
        super();
    }
        
    /**
     * returns null indicating that no driver specific implementation for 
     * QueryObjectGenerator available
     * @return null
     */
    public QueryObjectGenerator getQueryObjectGenerator() throws SQLException {
        return null;
    }
    
    /**
     * This method forwards all the calls to default query object provided by 
     * the jdk.
     * @param ifc interface to generated concreate class
     * @return concreat class generated by default qury object generator
     */
    public <T extends BaseQuery> T createQueryObject(Class<T> ifc) 
                                                    throws SQLException {
        return QueryObjectFactory.createDefaultQueryObject (ifc, this);
    } 
    
    /**
     * Intantiate and returns EmbedXAConnection.
     * @param user 
     * @param password 
     * @return XAConnection
     */
        protected XAConnection createXAConnection (ResourceAdapter ra, 
                String user, String password,
                boolean requestPassword)  throws SQLException {
            return new EmbedXAConnection40 (this, ra, user, 
                    password, requestPassword);
        }
}
