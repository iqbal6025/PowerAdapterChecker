package com.TaskSchedular.ChargerConnected;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TaskSchedular.kernal.Kernel32;

@Component
public class CheckAdapter {
	
	// variable use to control the flow
	int count=0;
   
	// call the karnal to check power is connected or not
	public boolean moniter() {
		
		Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
		Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
       
		return batteryStatus.isPlugged();
		
	}
	
	// cron expression use to call this method each second to check power is connect or not.
	@Scheduled(cron = "* * * * * *")
	public void result() throws IOException, InterruptedException {
		
		// file to write message isconnect or not
		File file = new File("connected.txt");
		
		Runtime runtime = Runtime.getRuntime();
		Process process;
		
       if(count%2==0) {
    	  
    	   // if power is connected then it open notepad and show message
		if(moniter()) {
			// create if file not exist
			if(file.createNewFile()) {
				FileWriter Write= new FileWriter(file);
				Write.write("Power Conntected !!!");
				Write.close();
				process=runtime.exec("notepad.exe connected.txt");
				Thread.sleep(10000);
				process.destroy();
				
			}
			else {
				process=runtime.exec("notepad.exe connected.txt");
				Thread.sleep(10000);
				process.destroy();
			}
			
			
		}
		count+=1;
       }
       else if(!moniter()) {
    	 
    	   count+=1;
       }
       
		
	}
}
