package com.example.Java_May_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
//Agenda： networking： tcp， udp， http/s ssl/tls
// tcp/ip model， osi model
// spring what is po.xml

//what is bean?

/**
 * what is object in java
 * object is managed by JVM
 * what is bean?
 * object is managed by spring ioc:
 *
 * maven commands:
 * mvn --version: print out the version of mvn
 * mvn clean: clears the target directory
 * mvn package : build the project and package the resulting JAR file into the target directory=
 * mvn install: build POM.xml file
 *
 * what is springboot annotation?
 * SpringBootApplication = springbootconfiguration + compnentScan + EnableAutoConfiguration
 * used to configure and define some behaviors of application.
 * help to simplify the configuration and development processing
 *
 * osi model: 7 layers
 *
 * suppose you are trying to send a request from you application to the cable
 * 	1: application layer: it is layer that close to the end-user, this layer will interacts with
 * 	software application and provides service for email, file transfer.
 * 	2:presentation layer: this layer will transforms the data into the form that the application can accept
 * 	data translation, data encryption and decryption, data compression, formatting
 *
 * 3 session layer: this layer will help you to manage and control the connections between devices/ client and server
 *
 * 4: transport layer: help you to transfer data between nodes
 * 			what is node?
 * 				can be any devices that can connect to a network: cellphone, printer, computers, server.
 *
 * 5: network layer: help protocols to transfer variable length data sequence from one node to another node in the same network
 * 		 variable length data sequence == datagram
 * 6: data link layer: data encode/ decode into bits.
 * 			logical link layer
 * 			mac layer
 * 	7: physical layer: it converts data bits into electrical impulses / radio signals -> fiber cable
 *
 *
 * TCP/ip model; 4 layers
 *
 * application layer
 * 	http, ftp, smtp snmp
 *
 * transport layer
 *  it is a backbone between the host's systems. TCP, UPD
 *
 * network layer / internet layer
 * sends the packets cross network
 *
 * network access layer
 * transfers the packets between different hosts.
 * encapsulate ip
 *
 *
 *
 * networking
 *
 * SYN:used to establish the connection between client and server
 *
 * ACK:let us know the other side(server side/ client side) that it has received the SYN #
 *
 *
 * FIN:to terminate the connection
 *
 * SYN-ACK:SYN message from local device and ACK of earlier packet
 *
 *
 * Seq:keep track how many data it has sent
 *
 *
 * TCP :
 *
 * client state   		 client 										server 				server state
 * LISTEN        1: choose seq and SYN -----SYN = 1, SEQ = x--------->								LISTEN
 *
 * 						<------(SYN = 1, ACK =1, SYN_RCVD, seq = y)----------------					SYN_RCVD
 *
 * ESTAB 			-----> ACK = 1 optional: data--->												ESTAB
 *
 * steps:
 * 	1: if you as a client want to establish a connection with the server side
 * 			you need to send: SYN = 1, seq = x, change the state from Listen to SYN_SENT
 *
 * 	2: the server responds to client request with SYN-ACK signal bits,
 * 			the server will change the state from listen to SYN_RCVD
 * 			ACK means the server received the last SYN from the client
 * 			SYN means what seq # you want to start segment(SYN = 1, ACK =1, SYN_RCVD , seq = y)
 *
 * 	3: the clients acknowledges the server sent something to me.
 * 		we are able to establish a reliable connection. we can start to transfer data
 * 		(optional: sometimes the client will send data with step 3, ACK = 1)
 * 		change state: client -> 	ESTAB, server -> ESTAB
 *
 *
 * 	why do we need three times?
 *
 *
 * 	if the first step is lost: client -> will resend SYN if the client does not receive SYN from the server for a fixed time
 * 	if the second step is lost: the client will redo the first step and the server will resend SYN and ACK
 *
 * TCP connection terminate
 * client state   		 client 										server 				server state
 * ESTAB							_____> FIN = 1, seq = x--->									ESTAB
 *
 * FIN_WAIT_1
 *
 * 									<--------ACK = 1											CLOSED_WAIT
 *
 * FIN_WAIT_2
 * -------------------------------if we still need some data from the server side, the server-----------
 * 	------------------------------still allow to send  data to the client side-------------------------
 * 									<-------fin = 1											LAST_ACK
 *
 * TIME_WAIT      				-------> ack = 1
 *
 *
 * CLOSE								wait for 2ms											CLOSE
 * steps:
 * 1: the client side send FIN(1) to the server side and change the state to FIN_WAIT_1
 * 2: the server side receive the fin and send the ack to the client, and set the state to CLOSED_WAIT
 * 	3: the client side receive the ack from the server and set the state to FIN_WAIT_2
 *  4: send fin to the client side and set state to LAST_ACK
 *5: client receives fin from the server and send ack to the server, set the state to TIME_WAIT
 *
 *6: wait some time and the client and server side go to close state
 *
 *
 * why four times?
 *
 * if the first handshake is lost:
 * 	the client will resend the fin to the server side
 *
 * 	if the step 2 is lost
 * 	resend fin and ack from the server to the client
 *
 * 	if the step 3 is lost
 * 	resend fin at the server
 *
 * 	if the step 4 is lost
 * 	resend fin at the server
 *
 *
 *
 *
 *
 * 	 udp: compare to TCP, udp does not need to build connection.
 * 	 steps
 * 	 1: date is prepared during the first step. supoose your application wants to send data, and your application
 * 	 		needs to prepare the datagram -> payload
 * 	 2:	datagram is sent: during this  step, the connection does not need to be established.
 * 	 						datagram : the source and destination address.
 * 	 3: datagram is received: for this 	step, the client does not know the datagram is received or lost.
 *
 * 	 4: data is extracted: the server/destination devices extract the data from the payload.
 *
 *
 *
 * 	 www.google.com -> 123.123.123.123
 *		DNS -> a naming system
 *		we can use it to translate human-readable domain name into IP address.
 *
 * 	 HTTP:
 * 	 what is http?
 * 	 		it is the foundation of any data exchange on the web
 * 	 	[your application ]
 * 	 			[wants to get a picture]
 * 	 			[wants to get a file]  -http->  [picture server, video server, ...]
 * 	 			[wants to get a video]
 *
 * 	 	http request message:
 * 	 				components: GET / HTTP/1.1 header: host....
 * 	 							GET is method, post, detele
 * 	 							/ is path
 * 	 							HTTP/1.1 version of the protocol
 * 	 							header is optional
 *
 * 	 	http response message
 * 	 			status code: 200
 * 	 			version of http
 * 	 		status message: ok
 * 	 		headers:
 *
 * 	 http methods : put get, post, head, delete, patch, connect, options
 * 	 	get fetch data, return a body
 * 	 	post: update data -> everytime you have post method, you will update whole data ->
 * 	 	patch: update data ->	 everytime you have put method, you will update some data
 * 	 	put: update data return context
 * 	 	delete: delete data
 *
 * 	 --
 * 	 head
 * 	 connect
 * 	 option
 *
 *
 * http status code:
 *  100 : continue
 *  101 : switch protocol
 *
 *  200 ok -> get post
 *
 *  204 no content -> no find anything but the request is read
 *
 *  302: the resource is found
 *
 *  400 bad request
 *
 *  404 not found
 *
 *  500 internal server error
 *
 *  502 bad gateway
 *
 * what is https?
 * http + ssl/tls
 *
 * user is using http -> no encryption
 * user is using https -> encrypted data -> server
 *
 * TCP happens before this encryption
 *
 * processing of encryption
 *
 * client side											server side: public key for encryption, private key for decryption
 *
 * 	1 random #			------Client hello ------>     1 random #
 *
 *		2 random #	   <-----Server hello -----       2 random
 *		CA				<-----2.1: certificate------
 *		public		   <---2.2: key exchange-------
 *						<----- server hello done----
 *
 *generate pre-master key ----->  this message is encrypted with public key -->  pre-master key
 *
 *
 *
 *
 * steps; 1 from 4 -> asymmetric encryption
 * 1: the client side wants to send CLIENT HELLO to build an encrypted connection
 * 					client supports TLS version(s) + cipher suite + first random #
 *
 * 2: the server receives CLIENT HELLO and starts to send server hello
 * 				CA stands for certificate authority: make sure client is able to be trusted
 * 			CA-> self-signed CA -> you will receive error
 *
 *3: client key exchange
 * the client side received server hello done. generate a pre-master key : random #
 *
 * 4: the client and server side receive pre-master key
 * session key = pre-master key + random # 1 + random # 2
 * session key on the server side = session key on the client side ->
 *
 * session key -> start to exchange data ->  symmetric encryption
 *
 * what is asymmetric encryption?
 *
 * public key is ued for encryption, private key is used for decryption
 * RSA ->
 * secure communication
 *
 * what is symmetric encryption?
 * one key is used for both encryption and decryption
 * faster than asymmetric encryption
 *  AES, DES,
 *  large amounts of data
 *
 *
 *
 *  ===========================spring boot======================
 *  why do we use spring boot?
 *  	less configuration -> able to quickly getting off the ground with spring boot based project
 *
 *  what is spring mvc?
 *   model-view - controller(MVC): software architectural pattern
 *   		model:  represents: data and business logic of your application: updating, processing, excapsulate data
 *   		VIEW: rendering the data from the model to the user: HTML< XML, Json
 *
 *   		controller: user -> request -> controller: handles user input and update the model and view.
 *   					an intermediary between the model and the view.
 *
 *
 *   Flexibility: add servers as much as you want to your application(easy to extend your application)
 *   reusability: bank service -> user service: each components can be reused across different application
 *   separation: makes your application easy to develop, lookup, test and maintian
 *
 *   // sample calculator
 *   // add, sub / divide / multiply : 4 restful apis
 *   // entity -> class
 *   //global exception handler -
 *
 *
 *   //
 *		the data type in json - key - value pair
 *			key must be a string ""
 *			value -> String -> {"name" : "Pengfei"}
 *					-> number in JSON much be an integer or a floating point
 *					-> object->{"people":{"name":"Pengfei", "age":25, "state": "IL"}}
 *					->	Array-> {"people" : ["pengfei", "Matt","Leo"]}
 *					-> boolean -> {"isPeople" : true}
 *					-> null -> {"people" : null}
 *
 *   {"result":-8.0}
 *
 *  put vs get vs delete vs pathc vs post
 *  getMapping: fetch/retrieve a result/resource from the server
 *  postMapping: creating a new resource on the server side
 *  putMapping: update an EXISTING resource on the server side
 *  deleteMapping: delete a resource on the server side
 *  patchMapping: PARTIALLY update an existing resource on the server side
 *
 *
 *  restController vs controller annotation
 *  	controller: it returns HTML views  -> view
 *  	 dispatcher servlet: handles all incoming http(s) requests and dispatches them to the
 *  	 	appropriate controller in your application(spring MVC framework)
 *
 *  	--->request -- step1->   dispatcher servlet <-- step 2-> handler mapping
 *  												<--step 3---> controller
 *  											<----step 4----> view resolver
 *  											<---step 5--->	view
 *
 *
 *
 *
 *  	restcontroller: it returns Json or XML
 *  --->request -- step1->   dispatcher servlet <-- step 2-> handler mapping
 *  												<--step 3 --> restcontroller
 *
 *
 *
 *  pathVariable vs requestBody vs requestParam
 *  pathVariable: fetch the values from the URL variable
 *  example: localhost:80/add/1/2 ->
 *
 *  requestBody
 *  you can use requestBody to test an object from the URL
 *
 *  ways to test: using postmant, or using this: curl -X GET -H "Content-Type: application/json" -d '{"result":33}' http://localhost:80/test
 *
 *
 * requestParam
 * used to fetch the parameters from URL
 * example: localhost:80/param?myCalObject=this is myCal
 *
 * myExeception class extends runtime exception
 *
 *
 * the components in spring servlet(dispatcher servlet)
 * controllers: handling incoming http requests and processing them,
 *
 * service: business logic of your application
 *
 * repository: handle the data access and persistence layer of your application
 *
 * model: data or state of ur application
 *
 * view: for rendering the response
 *
 *
 *
 * what is spring ioc
 * it is container that includes / stores all the beans
 *
 * ApplicationContext -> BeanFactory
 *
 * [ApplicationContext container -> CalculatorController bean]
 *
 * what is dependency injection:
 * @AutoWired -> fetch the bean from IOC by TYPE
 *
 * @Qualifier("yourBeanName") -> fetch the bean from IOC by NAME
 *
 * // interface: SoftwareEng -> sayHello()
 *
 *
 *
 * what are bean life cycles?
 * 1:
 * instantiate:  the spring IOC will create an instance of the bean by using either
 * 				constructor or a factory method
 *
 * 	2:
 * 		populate the properties
 *
 * 	3: beanPostProcessor: it is useful, when you want to customize bean creation before initialization
 *
 * 	4:
 *
 * 		initialization: initializing your bean
 *
 *
 * 	5: check whether or not you customized bean creation after initialization
 *
 * 	6: using -> bean
 *
 * 	7: termination
 *
 *
 * 	bean scope:
 * 	how to define the bean scope:
 * 		1: using annotation
 * 		2: using XML
 *
 * 	1: Singleton(default):
 * 		there is only one instance of the bean per spring container no matter how many requests you ask
 * 		<bean id ="youBeanName" class ="your.bean.path" scope="singleton"/>
 * 		<bean id ="youBeanName" class ="your.bean.path"/>
 *
 * 	2: prototype
 *
 * 		a new instance of the bean will be created each time it is requested from the container
 * 			<bean id ="youBeanName" class ="your.bean.path" scope="prototype"/>
 * 		the bean is not allowed to shared.
 * 		you are response for managing the life cycle of bean and release bean's resources
 * 	3: request
 * 		a new instance of the bean will be created when you are trying to do http request in a web application
 * 		the bean is related/ scoped to the life cycle of single http request
 *
 *			 @RequestScope
 * 			<bean id ="youBeanName" class ="your.bean.path" scope="request"/>
 *
 * 	4: session
 * 	a new instance of the bean will be created for each user session in a web application
 * 			scoped to the life cycle of http session
 * 			@SessionScope
 * 		<bean id ="youBeanName" class ="your.bean.path" scope="session"/>
 *
 * 	5: application bean scope
 * 			the container will help you create ONE instance of the bean during web application runtime
 * 				servletContext ->
 *
 *
 * 	session vs cookies
 * 	they are both used in your web application to maintain state and store some informations
 * 	related to users
 *
 * 	session: server side
 * 			the server will create a unique session for each user
 * 			session is stored in the server side
 * 			session is used to store user-specific information, like authentication
 * 			session is managed by the server
 *
 *
 * 	cookies:
 * 			it is stored on the user side
 * 			it is set by server and send back to client side
 * 			smaller data
 * 			login credentials information, user prefeneces
 * 			can be set with an expiration time.
 *
 *
 * step 1
 *
 * CREATE TABLE customer(
 * 	customer_id INT PRIMARY KEY,
 * 	first_name VARCHAR(255),
 * 		last_name VARCHAR(255),
 * 	age INT,
 * 	country VARCHAR(255)
 * )
 *
 * insert into customer(customer_id, first_name,last_name,age,country) values
 * (6, 'Jessica', 'Brown',35,'USA'), .......
 *
 *
 * step 2: create entity
 * snowflakes:
 *
 * @GeneratedValue(strategy = GenerationType.AUTO)
 * 	the persistence provider will choose the appropriate strategy based on the database
 *
 * @GeneratedValue(strategy = GenerationType.Identity)
 * 	auto-incrementing column in the datbase
 * @GeneratedValue(strategy = GenerationType.TABLE)
 * we will use the separate table to track the next available values
 * table -> my_ids{
 *
 *     1: 	32riuskfjgh
 *     2:  skjfdga232
 * }
 *
 * @GeneratedValue(strategy = GenerationType.SEQUENCE)
 *
 *  the primary key is generated by database sequence
 *
 * request -> controller -> service -> repo -> database
 *
 *setter injection:
 * 	you can change components during your application runtime
 *
 *  setMyObject(ThisISRepo thisIsRepo){
 *      this.thisIsRepo = thisIsRepo
 *  }
 *
 *  constructor inject
 *  	better to test, it is immutable -> thread safe if you have concurrent application
 *    private final CustomerRepo customerRepo;
 *     public CustomerServiceImpl(CustomerRepo customerRepo){
 *         this.customerRepo = customerRepo;
 *     }
 *
 *     field inject:
 *     you can directly inject components into the class
 *     drawback: hard to test, tight coupling
 *
 * @Autowire
 * CustomerRepo customerRepo;
 *
 *
 *
 * spring AOP
 *
 * system you need to consider aop:
 *
 *      logging, security, redis caching, transaction management, error handling,
 *      logger:
 *1. Aspect: An aspect encapsulates a cross-cutting concern, such as logging or transaction management. It contains advice and pointcut declarations.
 * 2. Advice: Advice represents the action taken by an aspect at a particular join point. It specifies what needs to be done and when it should be applied. Common advice types are **`@Before`**, **`@After`**, **`@AfterReturning`**, **`@AfterThrowing`**, and **`@Around`**.
 * 3. Join Point: A join point is a specific point during the execution of a program, such as method execution, exception handling, or field access. Join points are the target points where advice can be applie
 * 4. Pointcut: A pointcut is a predicate that matches one or more join points in the application. It defines the specific methods or locations in the code where advice should be applied. Pointcuts use expressions to define matching pattern
 * 5. Weaving: Weaving is the process of applying aspects to the target objects at the appropriate join points during the program execution. It can be done at compile-time, load-time, or runtime.
 * 6. AOPproxy vs target object：softwareService
 *
 *
 * testing
 *
 * unit testing: focus on single class, methods,
 *  frameworks: junit, mockito
 * integration testing: multiple classes, components,
 * frameworks: postman, spring boot Test
 *
 * TDD
 * how do you test witt restful apis
 *
 * how do you implement TDD
 * what is TDD: test driven development -> software development   waterfall software development
 *                                                              |-------<-----------some test failed-----|
 *                              features request -> write a test --test fail-> write logic code -all test pass->
 *                                  ->refactor: clean code -----> next features
 *
 * localhost:80/hello
 *
 *
 * spring validator
 *
 * NotNUll: element must not be null
 *
 * Size: the input size should be meets your reqirement
 *
 * @MIn: min value -> numeric
 * @Max maxvalue -> numeric
 *
 * @Email: email
 *
 * @Vlid: start to valid
 *
 * @NotBlank: string element must not be null or blank
 *
 *
 *
 */
@SpringBootApplication
public class JavaMaySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaMaySpringApplication.class, args);
//		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(JavaMaySpringApplication.class, args);
//		String[] myBeans = configurableApplicationContext.getBeanDefinitionNames();
//		for (String myBean : myBeans) {
//			System.out.println(myBean);
//		}
	}

}
