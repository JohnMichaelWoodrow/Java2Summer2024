package BlackJackProject;

/**
 * BlackJack Project
 * Message class represents a message with a type and associated data.
 * Provides methods to retrieve the type and data of the message.
 * Author: john-michael woodrow
 */

/**
 * Constructs a new Message with the specified type and data.
 */
public class Message {
    private String type;
    private Object data;

    /**
     * Constructs a new Message with the specified type and data.
     *
     * @param type The type of the message.
     * @param data The data associated with the message.
     */
    public Message(String type, Object data) {
        this.type = type;
        this.data = data;
    }

    /**
     * Returns the type of the message.
     *
     * @return The type of the message.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the data associated with the message.
     *
     * @return The data of the message.
     */
    public Object getData() {
        return data;
    }
}



