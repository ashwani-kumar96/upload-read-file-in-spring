# upload-read-file-in-spring

## @PostMapping("/upload-file")
-> If you hit this api, it will upload the file in the resource package static->show-file folder.
   (what type of file will be uploaded, it's mentioned in the code.Like, csv or ods e.t.c).

-> In response you will get the "url" of that file.

    SAMPLE RESPONSE :
   (e.g : http://localhost:8080/show-filebu-fi.csv)
---------------------------------------------------------------------------

## @PostMapping("/file-upload")
-> If you hit this api, it will upload the file in target folder.

-> In response you will get the "message" of that file.

    SAMPLE RESPONSE :
   (e.g : File uploaded successfully)
-----------------------------------------------------------------------

## @PostMapping("/readByRow")
-> By using this api, you will be able to upload the file and read the particular row.
   (user needs to give the row number, from which row he wants to read the data)

   SAMPLE RESPONSE :
   i) First, you hit this api from postman, then
   ii) Choose the file, which you want to upload, then send the request
   iii) After that, on the console it will ask for row number
   (e.g : Enter row number : 5)
   iv) Once you enter the row number and press submit, you will get the response like this,
        [
            "BDCQ.SF1AA2CA",
            "2017.06",
            "1233.7",
            "",
            "F",
            "Dollars",
            "6",
            "Business Data Collection - BDC",
            "Industry by financial variable (NZSIOC Level 2)",
            "Sales (operating income)",
            "Forestry and Logging",
            "Current prices",
            "Unadjusted",
            ""
        ]

        v) The above response shows the 5th row data of my csv file.
------------------------------------------------------------------------------------

## @GetMapping("/xml-to-json")
-> If you hit this api, it will convert your xml file into json. And it will show the json on your console.

    SAMPLE INPUT : XML File
    --------------------------
    <?xml version="1.0" ?>
    <root>
    <test attribute="text1">springboot</test>
    <test attribute="text2">JTP</test>
    </root>

   SAMPLE OUTPUT : JSON File
   ------------------------
   {
   "root":{
   "test":[{
   "attribute":"text1",
   "content":"springboot"
   },
   {
   "attribute":"text2",
   "content":"JTP"
   }
   ]
   }
   }
-----------------------------------------------------------------
=> You will find the all apis in controller package.
=> File upload logic in helper package.
=> Convert Xml to Json code in services package.
=> And you can also find the uploaded csv file in inside resources folder of static.
