#ifndef GHOSTLAB_TYPES_H
#define GHOSTLAB_TYPES_H

// Includes integer types (`uint8_t`, `uint16_t`, `uint32_t`, `uint64_t`, `int8_t`, `int16_t`, `int32_t`, `int64_t`).
#include <stdint.h>
// Include boolean type.
#include <stdbool.h>
// Include variadic types.
#include <stdarg.h>
// Include `size_t`.
#include <stddef.h>

// The name of the currently built executable (used for logging purposes).
#ifdef GHOSTLAB_CLIENT
#define GHOSTLAB_EXECUTABLE_NAME "client"
#else
#define GHOSTLAB_EXECUTABLE_NAME "server"
#endif

// Asserts a condition that returns `-1` in case of error.
#define gl_assert(condition) if (!(condition)) { return -1; } (void)0

// Asserts a condition that terminates the program in case of error.
#define gl_fatal_assert(condition) if (!(condition)) { goto error; } (void)0

// Describes a buffer (a structure to send data through a socket).
typedef struct gl_buffer_t {
    // Length of the buffer.
    uint16_t length;
    
    // Data of the buffer.
    uint8_t *data;
} gl_buffer_t;

#endif /* GHOSTLAB_TYPES_H */