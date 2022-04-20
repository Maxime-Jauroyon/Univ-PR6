#include <client/thread_multicast_game_listener.h>
#include <sys/socket.h>
#include <common/log.h>
#include <common/game.h>
#include <common/message.h>
#include <common/network.h>
#include <client/shared.h>

void *gl_client_thread_multicast_game_listener_main(void *user_data) {
    gl_log_push("multicast game thread started.\n");
    
    g_multicast_general_socket = gl_socket_create(g_multicast_ip, gl_client_get_game()->multicast_port, GL_SOCKET_TYPE_MULTICAST_RECEIVER, 0);
    
    while (!g_should_quit) {
        if (gl_message_wait_and_execute(g_multicast_general_socket, GL_MESSAGE_PROTOCOL_UDP) < 0) {
            break;
        }
    }
    
    gl_socket_close(&g_multicast_general_socket);
    
    gl_log_push("multicast game thread stopped.\n");
    
    return 0;
}