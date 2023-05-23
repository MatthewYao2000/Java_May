package com.example.Java_May_Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
 *
 *
 */
@SpringBootApplication
public class JavaMaySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaMaySpringApplication.class, args);
	}

}
