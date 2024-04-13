package com.thaisbg.campaignpoints.tweets.model;

public class User {
    private String id;
    private String email;
}

/*
To populate the user table, the following script was used in surrealist.app:
-- Command to create schemafull table
DEFINE TABLE users SCHEMAFULL;

-- Command to add the email field
DEFINE FIELD email ON TABLE users TYPE string
	ASSERT string::is::email($value);
DEFINE INDEX userEmailIndex ON TABLE users COLUMNS email UNIQUE;

-- Commands to add some users
CREATE users:nick SET email = 'nick@mail.com';
CREATE users:david SET email = 'david@mail.com';
CREATE users:alice SET email = 'alice@mail.com';
CREATE users:mary SET email = 'mary@mail.com';
 */
