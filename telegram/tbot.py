# -*- coding: utf-8 -*-

import requests  
from bottle import (  
    run, post, response, request as bottle_request
)
BOT_URL = 'https://api.telegram.org/bot1158270282:AAF9yMHV6YVq-VInLR-ZFMCrTaaniE4joO8/'
MY_URL = 'http://localhost:9015/'
@post('/')  # our python function based endpoint
def main():
    data = bottle_request.json
    answer_data = prepare_data_for_answer(data)
    send_message_telegram(answer_data)
    send_message_mySystem(data)

def get_chat_id(data):
    chat_id = data['message']['chat']['id']
    return chat_id

def get_message(data):
    message_text = data['message']['text']
    return message_text

def prepare_data_for_answer(data):  
    json_data = {
        "chat_id": get_chat_id(data),
        "text": "Confirmed",
    }
    return json_data

def send_message_telegram(prepared_data):  
    """
    Prepared data should be json which includes at least `chat_id` and `text`
    """ 
    message_url = BOT_URL + 'sendMessage'
    requests.post(message_url, json=prepared_data)

def send_message_mySystem(data):
    message_url = MY_URL + 'words'
    json = {
        "german": get_message(data),
        "russian": "",
        "other": 1
    }
    print(json)
    requests.post(message_url, json=json) 


if __name__ == '__main__':  
    run(host='localhost', port=8080, debug=True)