import md.MessageService

// we don't need a getMessage() because it's a groovy accessor
// by default.
class cgmessage implements MessageService {
    String message
}
