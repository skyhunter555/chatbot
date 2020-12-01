package simple.chatbot.entity.reaction;


import simple.chatbot.entity.response.MessageReceived;

public interface IChatbotReaction {

    MessageReceived getOriginalMessage();

}
