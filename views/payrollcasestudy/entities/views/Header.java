package payrollcasestudy.entities.views;

public class Header {

	public Header() {
	}
	
	public String header(){
		return "<!DOCTYPE html>"
				+"<html>"
				+"<head>"
				+"<title>TITULO</title>"
				+ "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"
				+ "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js'></script>"
				+ "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>"
				+"</head>";
		}

}