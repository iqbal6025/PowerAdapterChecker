package com.TaskSchedular.ChargerConnected;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.TaskSchedular.SleepMode.Kernel32;

@Component
public class CheckAdapter {
	
	int count=0;
 
	public boolean moniter() {
		
		Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
		Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
       
		return batteryStatus.isPlugged();
		
	}
	
	@Scheduled(cron = "* * * * * *")
	public void result() throws IOException, InterruptedException {
		
		File file = new File("C:\\Users\\mh62255\\Desktop\\FileHandling\\connected.txt");
		
		
		Runtime runtime = Runtime.getRuntime();
		Process process;
       if(count%2==0) {
    	   
		if(moniter()) {
			if(file.createNewFile()) {
				FileWriter Write= new FileWriter(file);
				Write.write("Power Conntected !!!");
				Write.close();
				process=runtime.exec("notepad.exe C:\\Users\\mh62255\\Desktop\\FileHandling\\connected.txt");
				Thread.sleep(10000);
				process.destroy();
				
			}
			else {
				process=runtime.exec("notepad.exe C:\\Users\\mh62255\\Desktop\\FileHandling\\connected.txt");
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
