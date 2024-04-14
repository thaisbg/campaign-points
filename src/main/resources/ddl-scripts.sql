REMOVE TABLE IF EXISTS users;
REMOVE TABLE IF EXISTS tweets;
REMOVE TABLE IF EXISTS campaigns;
REMOVE TABLE IF EXISTS score;
REMOVE TABLE IF EXISTS score_history;
--------------------------------------------------------------------
-- USERS
--------------------------------------------------------------------
-- Create schemafull users table.
DEFINE TABLE users SCHEMAFULL;

-- Define fields.
DEFINE FIELD email ON TABLE users TYPE string
	ASSERT string::is::email($value);
DEFINE INDEX userEmailIndex ON TABLE users COLUMNS email UNIQUE;

-- Insert data
CREATE users:nick SET email = 'nick@mail.com';
CREATE users:david SET email = 'david@mail.com';
CREATE users:alice SET email = 'alice@mail.com';
CREATE users:mary SET email = 'mary@mail.com';

--------------------------------------------------------------------
-- TWEETS TABLE
--------------------------------------------------------------------
-- Create schemafull tweets table.
DEFINE TABLE tweets SCHEMAFULL;

-- Define fields.
DEFINE FIELD payload ON TABLE tweets TYPE string;
DEFINE FIELD timestamp ON TABLE tweets TYPE datetime;
DEFINE FIELD userId ON TABLE tweets TYPE record<users>;

--------------------------------------------------------------------
-- CAMPAIGN TABLE
--------------------------------------------------------------------
-- Create schemafull campaigns table.
DEFINE TABLE campaigns SCHEMAFULL;

-- Define fields.
DEFINE FIELD phrase ON TABLE campaigns TYPE string;
DEFINE FIELD creation ON TABLE campaigns TYPE datetime;
DEFINE FIELD alteration ON TABLE campaigns TYPE datetime;
DEFINE FIELD expiration ON TABLE campaigns TYPE datetime;

--------------------------------------------------------------------
-- SCORE TABLE
--------------------------------------------------------------------
-- Create schemafull tweets table.
DEFINE TABLE score SCHEMAFULL;

-- Define fields.
DEFINE FIELD userId ON TABLE score TYPE record<users>;
DEFINE FIELD score ON TABLE score TYPE int;

--------------------------------------------------------------------
-- SCORE TABLE
--------------------------------------------------------------------
-- Create schemafull tweets table.
DEFINE TABLE score_history SCHEMAFULL;

-- Define fields.
DEFINE FIELD tweetId ON TABLE score_history TYPE record<tweets>;
DEFINE FIELD campaignId ON TABLE score_history TYPE record<campaigns>;
DEFINE FIELD points ON TABLE score_history TYPE int;