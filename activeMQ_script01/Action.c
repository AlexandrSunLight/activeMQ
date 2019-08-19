Action()
{
	jms_send_message_queue ("step1: send message",
	"Test Message001!",
	"dynamicQueues/{QueueName}");

	return 0;
}



