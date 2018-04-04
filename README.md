# *Wessenger*

### Instant messaging application on Java.

This is a messaging application with a simple GUI. It consists of basicly two parts.
One is the `Server` under the `server` directory. You should compile and run it before
everything:

```
$ cd server
$ javac Server.java
$ java Server 9898 # Server will listen on port 9898.
```

The other part is the application's itself. It needs a database (name of the database is unfortunately hardcoded
as `squirrel.sqlite`) to run. You can create the database from `db.ddl` file, which is under `ss/` directory.

The database should be located alongside the `run.sh` script. Additionally, you need [sqlite-jdbc-3.21.0.jar](https://bitbucket.org/xerial/sqlite-jdbc/downloads/)
 file under `lib/` folder.

 After these requirements have successfully been satisfied, you can compile/run the application with `run.sh`:

 ```
 $ ./run.sh
 ```

 Hopefully, you will see the login window.

![Login window.](/ss/login.png?raw=true)

After you entered your user name and local address, you will be welcomed with a window like this:

![Main window.](/ss/main_window.png?raw=true)

Well, here we have four logical parts of facility. First, the menu bar. We have got these kinds of menus:

![Menus](/ss/menus.png?raw=true)

We can open new sessions with single users or groups. Group sessions work in a `multicasting` fashion.

![New Session 0](ss/new_session0.png?raw=true)
![New Session](ss/new_session.png?raw=true)

After opening a session, we can start talking with user/s by selecting the session in which we want to send messages.
User's messages are somewhat distinguished from messages from the other users in the session.

Here is a conversation of three Hobbits. From Peregrin's view:

![Pippin](ss/peregrin.png?raw=true)

From Sam's view:

![Sam](ss/sam.png?raw=true)

And from Frodo's view:

![Frodo](ss/frodo.png?raw=true)

You can also search within all the past messages via search dialog opened under `SEARCH` menu.

![Search1](ss/search.png?raw=true)

Deleting the sessions is also starightforward, through the `Delete Session` dialog located under `SESSIONS` menu.


Please note that, this application hardly ever does any healthchecks. The main purpose in developing this application was to
practice in Java socket programming. If you issue the command in the right order, you should be good to go.

After starting the `Server` program, it listens on the specified port. When a client starts running, it sends an information 
message to the server to be registered with its ip and port address. So does other clients. Server has a hash table in which
it keeps user-address matchings. So after each demonstration, it will be better to restart the server. 

Here is an output from the server's log:

![Log](/ss/log.png?raw=true)

Any contributions are welcome for the issue above or any other bugs in the application.
I hope you find it useful.

Thanks.
