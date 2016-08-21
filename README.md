# DataMonster File Indexer




### Getting Started
```bash
Please change the WATCH_FOLDER in com.datamonster.utils.DataMonsterQueryConstants 

$ mvn clean test

# To start the application
$ mvn exec:java


The  services runs on port 8080 by default and you can query the data

| API | Type | Operation |
| --- |------|--- |
| /search?url=...| GET | Returns the list of filenames along with Version in descending order in table format. By Default it lists only three latest versions|
| /raw?url=...&version...| GET | Returns the HTML content of the searched url version number|
| /search/?url=...&limit=..| GET | Returns the list of filenames along with Version in descending order in table format.Lists the latest 'n' version as per requested|
| /purge?url=...&keeponlyrecent...| GET | Deletes the HTML file from folder mentioned and keep only latest 'n' versions as per request.|




<hr />

### Problem Description

At Indix we do a lot of crawling to get the product data, along with their latest prices. The information are extracted using what we call as parsers. One of the most common issue we face while parsing is site changing their templates. This introduces what we call as "data bugs". We end up capturing a related product's price as the price of the product page and these are very hard to catch. For debugging these kind of issues, you need access to the HTML page that was crawled at that point in time to see why the parser has failed.



### System Requirements


- The system should consume the HTML files which are dropped into a shared folder. The files can be dropped in at any point of time.
- The system should be able to index the html pages of different domains.
- The system should support purging HTML versions older than last 3 versions for a given URL.
- The system should be scalable to support large number of HTML pages


### Technology

Java


### To Do 
...later...