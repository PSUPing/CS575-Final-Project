Virus Information DB
====================

Instructions
=-=-=-=-=-=-
In order to use this solution, go to http://www.github.com/CS575-Final-Project/ and clone the contents to your desktop.

Open a terminal and navigate to the “CS575-Final-Project” directory that you just cloned.  

Run “./activator run” (or “activator.bat run” if on Windows).

Navigate to “http://localhost:9000/“ in the browser of your choice.

Video Link
=-=-=-=-=-
You can view a video of this application being used at http://www.youtube.com/<insert-link-here>

Document
=-=-=-=-
It is included in the GitHub repository.  Alternatively, it was also submitted as part of the BBLearn submission.

Addendum
========
Please note that if you wish to use your own implementation of a MongoDB, you will have to modify the following files and parameters: 

File					     		Parameter
----					     		---------
/CS575-Final-Project/conf/application.conf   		mongodb.uri
/CS575-Final-Project/app/controllers/Viruses.scala	*def collection..(“virus_info_db”)

*Please note that this change is only necessary if you wish to have a different MongoDB collection name than “virus_info_db”.