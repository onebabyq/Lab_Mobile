<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <form action="SendingMail" method="POST" style="background-color: white;">
        <fieldset> 
            <legend>MAIL SENDING INFOMATION FORM</legend>
            <div>
                <label for="to"></label>To:   
                <br>
                <input type="text" name="to" size="78">
            </div>
            <br>
            <div>
                <label for="subject"></label>Subject:
                <br>
                <input type="text" name="subject" size="78">
            </div>
            <br>
            <div>
                <label for="body_message"></label>Body message:
                <br>
                <textarea name="msg" rows="15" cols="80">
                </textarea>
            </div>
            <br>
            <div>
                <input type="submit" name="submit">
                <input type="reset" name="reset">
            </div>
        </fieldset>
    </form>
</body>
</html>