# Moneylog
Steamini is an Android app to get game recommendations based on your most played games on Steam.<br>

## Setup
**You need to provide your own API keys**, you will need a Steam API key and a Gemini API key.

### *Why is that?*
API keys are personal and should not be shared, this is the only way for the app to work without a dedicated server-side.

### *How do I get the API keys?*
You can get a Steam API key [here](https://steamcommunity.com/dev/apikey) and a Gemini API key [here](https://ai.google.dev/gemini-api/docs/api-key), both are free and very quick to obtain.

### *Where do I put them?*
In the `local.properties` file, like this:
```
GEMINI_API_KEY=YOUR_KEY_HERE
STEAM_API_KEY=YOUR_KEY_HERE
```

# How to use
You need the **64 bit Steam ID** of a user, put it on the text field and click the button. Steamini will obtain your most played games from Steam API and then ask Gemini API for recommendations.

### *How do I get a user's ID?*
Go to a user's profile page on Steam and copy the URL, then go to [this site](https://profile.tf/) and paste it there, it will show you that user's 64 bit Steam ID.

# Screenshot
![Screenshot_20240512_011050](https://github.com/leomarques/steamini/assets/1104925/6ae8066a-1498-49fc-a54d-783d0887f5d8)

## License

This application is released under MIT license (see [LICENSE](LICENSE)).<br>
Some of the used libraries are released under different licenses.
