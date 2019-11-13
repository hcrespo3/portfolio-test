# portfolio test

# Setting up environment
Please install Tomcat 9 and follow the instructions to setting up your environment variables
You will need git installed in order to clone the repository

# Cloning repository
You will find project files in the following github public repository:
https://github.com/hcrespo3/portfolio-test.git 

# Setting up the project
- Install maven dependencies
- Set your profile to dev

# Deploying app
- You will find a directory in the repository called "deploy" containing a zip with the war file as well as
Tomcat configuration files.
- Replace configuration files with the two files provided.  
- Copy the war generated to your tomcat public directory (usually webapps)

# Time of completion
It took 8hrs to complete this test
I was trying to include test containers, database rider, flyway and dbUnit for tests
but I have to keep this changes out at the end, due to time.

# You can go to the following route trough the browser to get to the Portfolio simple page:
- /zemoga-portfolio/{portfolio-id}
I used Thymeleaf instead of JSP to rendering data

# Three endpoints were provided for api:
- /zemoga-portfolio-api/user-info/{portfolio-id}
- /zemoga-portfolio-api/user-info/{portfolio-id}
- /zemoga-portfolio-api/get-tweets/{twitter-username}/{count}

You could use twitter endpoint to bring up the timeline for any account


I couldn't complete the styles, so the view will display information almost in a raw way