import requests
from bs4 import BeautifulSoup
import sys
import base64
import json
from simple_salesforce import Salesforce


url = "https://www.local.com/cgi-bin/viewissued.cgi?"
def get_lic_info():
	r= requests.get(url, verify=False)
	req=r.content
	soup = BeautifulSoup(req)
	#creates text file
	target = open ('LicenseInfo.txt',"wb")
	allrows = soup.findAll('tr')
	y=len(soup.findAll('tr'))
	x=len(soup.findAll('tr')) - 4
	if y > 4:
		for row in range(4,y):
			allcols = allrows[row].findAll('td')
			lic_num = allcols[2].findAll(text=True)
			lic_info = allcols[3].findAll(text=True)
			end_date = allcols[5].findAll(text=True)
			result = (lic_num[0] + lic_info[0] + end_date[0])
			target = open ('LicenseInfo.txt', 'a')
			target.write(result.encode('utf8'))

			target.close()

def upload_to_sf():
	
	sf=Salesforce(username='nigel@mcintyre.com.leads', password='juniperBerries2', security_token='RLKnddwz9cdf2qIjEodsdddH7', sandbox = True)
	sessionId = sf.session_id

	body = ""
	with open("LicenseInfo.txt", "r") as f:
		body = base64.b64encode(f.read())

	response = requests.post('https://salesforce.com/services/v23.0/sobjects/Document/',
		headers = { 'Content-type': 'application/json', 'Authorization':'Bearer %s' % sessionId},
		data = json.dumps({
			'Description':'License Information',
			'Keywords':'License, Information',
			'FolderId': '00lg012343000MQykAAG',
			'Name': 'licenseInfo',
			'Type':'txt',
			'body': body
			})
		)
	
	print response.text

def main():
	get_lic_info();
	upload_to_sf();

main()







