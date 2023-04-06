<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>File Upload Example</title>
</head>
<body>
	<form action="upload" method="post" enctype="multipart/form-data">
		<label for="file">Select a file:</label>
		<input type="file" id="file" name="file" accept=".txt, .pdf, .doc, .docx"><br><br>
		<input type="submit" value="Upload">
	</form>
</body>
</html>
