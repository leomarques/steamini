# Steamini
Steamini is an Android app to get game recommendations based on your most played games on Steam.<br>

## Setup
**You need to provide your own API keys**, you will need a Steam API key and a Gemini API key.

### Why is that?
Since API keys are personal and should not be shared, this is the only way for the app to work without a server-side.

### How do I get the API keys?
You can get a Steam API key [here](https://steamcommunity.com/dev/apikey) and a Gemini API key [here](https://ai.google.dev/gemini-api/docs/api-key), both are free and very quick to obtain.

### Where do I put them?
In the `local.properties` file located on project root (not versioned), like this:
```
GEMINI_API_KEY=your_gemini_key_here
STEAM_API_KEY=your_steam_key_here
```

## How to use
You need the **64 bit Steam ID** of a user, put it on the text field and click the button. Steamini will obtain your most played games from Steam and then ask Gemini for recommendations.

### How do I get a user's ID?
Go to a user's profile page on Steam and copy the URL, then go to [this site](https://profile.tf/) and paste it there, it will show you that user's 64 bit Steam ID.

## Screenshot
![25](https://github.com/leomarques/steamini/assets/1104925/60b4e8d9-ba46-4e8e-945b-17408a18cae0)

## License
This application is released under MIT license (see [LICENSE](LICENSE)).<br>
Some of the used libraries are released under different licenses.
