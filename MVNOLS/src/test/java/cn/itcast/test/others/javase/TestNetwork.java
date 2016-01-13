package cn.itcast.test.others.javase;

import org.junit.Test;

import cn.itcast.test.others.javase.testclass.network.tcp.Client;


/**
 * 	This is the class to perform testing regarding network programming
 * 
 * 	Network programming: resolve the communciation between computers (laptops, cellphones, etc.); C-S model oriented
 *  -- 3 elements: IP, port, protocol
 *  -- Computer network: the system where computers distributed in different areas can communicate & share resources mutually by external devices
 *  Web programming: resolve the communication based on html (browser, etc.); B-S model oriented
 *  -- 3 layers: web(servlet|filter|listener)|action|controller, service|business, DAO(Data Access Object)
 *  
 *  For JavaWeb field: proficient in web programming & network theory, familiar with network programming (*****)
 *  For Android field: proficient in network programming & network theory, familiar with web programming
 *
 * 	TCP (Transmission Control Protocol) features：
 * 	1. I/O-stream based
 * 	2. No size limitation during transmission。
 * 	3. Cconnection communication: making phone call, transmitting files, download, etc.
 *     -- 3-way handshake: ensure integrity & realiability 
 *     -- Relatively slower compared with UDP
 * 	4. Distinct client & server 
 *     -- Both client & server can be both transmitter & receiver
 *  
 *  UDP (User Datagram Protocol) features：
 * 	1. Packet based
 * 	2. Max size is 64K for each packet during transmission
 * 	3. Connectionless communication: writing letters, etc.
 * 	   -- Not guarantee integrity & realiability
 *     -- Relatively faster compared with TCP
 * 	4. Does not distinct client & server
 *     -- Only distinct transmitter & receiver
 *     
 *  About network:
 *  1. Load balancing
 *  2. Network|Broadcast radiation: 
 *  3. Seven layers: 
 *     -- application: App (Skype, etc.)
 *        -- protocol: HTTP/DNS
 *        -- software: OS (Operating System)
 *        -- hardware: laptop, desktop, server
 *     -- representation: ASCII
 *        -- software: OS
 *        -- hardware: laptop, desktop, server
 *     -- session: SQL, RPC; 
 *        -- software: MySQL, Oracle, OS
 *        -- hardware: laptop, desktop, server
 *     -- transport: LVS 
 *        -- protocol: TCP/UDP 
 *        -- software: OS
 *        -- hardware: laptop, desktop, server
 *     -- network: IP 
 *        -- protocol: IPv4, IPv6
 *        -- hardware: router
 *     -- data link: MAC address 
 *        -- protocol: ARP (in IPv4); NDP (in IPv6)
 *        -- hardware: switch, NIC(Network Interface Controller)
 *     -- physical: binary bit stream
 *        -- hardware: coaxial cable, fiber, NIC
 */
public class TestNetwork {
	
	/**
	 * 	Test the TCP client
	 * 	1. TCP server is started through command line shell
	 * @throws Exception
	 */
	@Test
	public void testTCPClient () throws Exception {
		Client.main(null);
	}
}
