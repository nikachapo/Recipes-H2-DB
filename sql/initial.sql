CREATE TABLE user (
    mail VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
)

CREATE TABLE recipe ( ownerMail VARCHAR(50),
                      title VARCHAR(50),
                      makingTime DOUBLE,
                      ingredients VARCHAR
)