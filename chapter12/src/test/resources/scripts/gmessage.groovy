import md.MessageService

class GMessage implements MessageService {
    @Override
    String getMessage() {
        return 'hello world, man'
    }
}
