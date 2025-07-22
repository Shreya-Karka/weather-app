import requests
import os
from dotenv import load_dotenv

load_dotenv()  # Load from .env file

API_KEY = os.getenv("OPENWEATHER_API_KEY")

def get_weather(city, api_key):
    url = f"http://api.openweathermap.org/data/2.5/weather?q={city}&appid={api_key}&units=metric"
    response = requests.get(url)


    if response.status_code == 200:
        data = response.json()
        print(f"\n📍 City: {data['name']}, {data['sys']['country']}")
        print(f"🌡️ Temperature: {data['main']['temp']}°C")
        print(f"🌤️ Weather: {data['weather'][0]['description'].title()}")
        print(f"💧 Humidity: {data['main']['humidity']}%")
        print(f"💨 Wind Speed: {data['wind']['speed']} m/s")
    else:
        print("❌ Error fetching weather data. Please check the city name.")

if __name__ == "__main__":
    city = input("Enter city name: ")
    get_weather(city, API_KEY)
