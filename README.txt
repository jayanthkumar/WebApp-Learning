To debug application remotely in windows, run the below command before running jettyRun
	set GRADLE_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,address=9999,server=y,suspend=n
Add debug connection properties host as 'localhost' and port as '9999' if you are running the application locally.