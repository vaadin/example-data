#!/bin/bash

width=200
height=$(($width*2))

rm -f src/main/resources/com/vaadin/exampledata/BookImageBackground.txt
for baseurl in `cat src/main/resources/com/vaadin/exampledata/BookImageBackground.src`;
do 
  url="$baseurl?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$width&h=$height&q=80"
  echo "Downloading $url"
  (echo -n "data:image/png;base64," ; curl -s "$url"|base64  ) >>  src/main/resources/com/vaadin/exampledata/BookImageBackground.txt
done
