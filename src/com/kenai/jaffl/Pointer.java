/*
 * Copyright (C) 2008-2011 Wayne Meissner
 *
 * This file is part of the JNR project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kenai.jaffl;

import java.nio.charset.Charset;

public interface Pointer {
    /**
     * Indicates whether or not this memory object represents a native memory address.
     *
     * <p>Memory objects can be either direct (representing native memory), or
     * non-direct (representing java heap memory).
     *
     * <p>Non-direct memory objects can still be passed to native functions as pointer
     * (void *, char *, etc) parameters, but the java memory will first be copied
     * to a temporary native memory area.  The temporary memory area will then be
     * used as the parameter value for the call.  If needed, the java memory
     * will be automatically reloaded from the temporary native memory after the
     * native function returns.
     * <p><b>Note:</b> the transient nature of the temporary memory allocated for
     * non-direct memory means native functions which store the address value
     * passed to them will fail in unpredictable ways when using non-direct memory.
     * You will need to explicitly allocate direct memory to use those types of
     * functions.
     *
     * @return true if, and only if, this memory object represents a native address.
     */
    abstract public boolean isDirect();

    /**
     * Gets the native address of this memory object (optional operation).
     *
     * @return the native address of this memory object.  If this object is not
     * a native memory address, an address of zero is returned.
     */
    abstract public long address();

    /**
     * Gets the size of this memory object (optional operation).
     *
     * @return the size of the memory area this {@code Pointer} points to.  If
     * the size is unknown, {@link java.lang.Long#MAX_VALUE} is returned}.
     */
    abstract public long size();

    /**
     * Gets the {@link Runtime} this {@code Pointer} instance belongs to.
     *
     * @return the {@code Runtime} instance of this {@code Pointer}.
     */
    abstract public Runtime getRuntime();

    /**
     * Reads an {@code byte} (8 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the {@code byte} value at the offset.
     */
    abstract public byte getByte(long offset);

    /**
     * Reads a {@code short} (16 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the {@code short} value at the offset.
     */
    abstract public short getShort(long offset);

    /**
     * Reads an {@code int} (32 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the {@code int} value contained in the memory at the offset.
     */
    abstract public int getInt(long offset);

    /**
     * Reads a {@code long} (64 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the {@code long} value at the offset.
     */
    abstract public long getLong(long offset);
    
    /**
     * Reads a {@code long} (64 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the {@code long} value at the offset.
     */
    abstract public long getLongLong(long offset);

    /**
     * Reads a {@code float} (32 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the {@code float} value at the offset.
     */
    abstract public float getFloat(long offset);

    /**
     * Reads a {@code double} (64 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the {@code double} value at the offset.
     */
    abstract public double getDouble(long offset);

    /**
     * Reads a native {@code long} value at the given offset.
     * <p>A native {@code long} can be either 32 or 64 bits in size, depending
     * on the cpu architecture, and the C ABI in use.
     *
     * <p>For windows, a long is always 32 bits (4 bytes) in size, but on unix
     * systems, a long on a 32bit system is 32 bits, and on a 64bit system, is 64 bits.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the native {@code long} value at the offset.
     *
     * @see NativeLong
     */
    abstract public long getNativeLong(long offset);

    /**
     * Writes a {@code byte} (8 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param value the {@code byte} value to be written.
     */
    abstract public void putByte(long offset, byte value);

    /**
     * Writes a {@code short} (16 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param value the {@code short} value to be written.
     */
    abstract public void putShort(long offset, short value);

    /**
     * Writes an {@code int} (32 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param value the {@code int} value to be written.
     */
    abstract public void putInt(long offset, int value);

    /**
     * Writes a {@code long} (64 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param value the {@code long} value to be written.
     */
    abstract public void putLong(long offset, long value);
    
    /**
     * Writes a {@code long} (64 bit) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param value the {@code long} value to be written.
     */
    abstract public void putLongLong(long offset, long value);

    /**
     * Writes a {@code float} (32 bit, single precision) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param value the {@code float} value to be written.
     */
    abstract public void putFloat(long offset, float value);

    /**
     * Writes a {@code double} (64 bit, double precision) value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param value the {@code double} value to be written.
     */
    abstract public void putDouble(long offset, double value);

    /**
     * Writes a native {@code long} value at the given offset.
     *
     * <p>A native {@code long} can be either 32 or 64 bits in size, depending
     * on the cpu architecture, and the C ABI in use.
     *
     * <p>For windows, a long is always 32 bits (4 bytes) in size, but on unix
     * systems, a long on a 32bit system is 32 bits, and on a 64bit system, is 64 bits.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param value the native {@code long} value to be written.
     */
    abstract public void putNativeLong(long offset, long value);
    
    abstract public long getAddress(long offset);
    abstract public void putAddress(long offset, long value);
    abstract public void putAddress(long offset, Address value);

    /**
     * Bulk get method for multiple {@code byte} values.
     *
     * This method reads multiple {@code byte} values from consecutive addresses,
     * beginning at the given offset, and stores them in an array.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the first value will be read.
     * @param dst the array into which values are to be stored.
     * @param idx the start index in the {@code dst} array to begin storing the values.
     * @param len the number of values to be read.
     */
    abstract public void get(long offset, byte[] dst, int idx, int len);

    /**
     * Bulk put method for multiple {@code byte} values.
     *
     * This method writes multiple {@code byte} values to consecutive addresses,
     * beginning at the given offset, from an array.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the first value will be written.
     * @param dst the array to get values from.
     * @param idx the start index in the {@code dst} array to begin reading values.
     * @param len the number of values to be written.
     */
    abstract public void put(long offset, byte[] dst, int idx, int len);

    /**
     * Bulk get method for multiple {@code short} values.
     *
     * This method reads multiple {@code short} values from consecutive addresses,
     * beginning at the given offset, and stores them in an array.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the first value will be read.
     * @param dst The array into which values are to be stored.
     * @param idx the start index in the {@code dst} array to begin storing the values.
     * @param len the number of values to be read.
     */
    abstract public void get(long offset, short[] dst, int idx, int len);

    /**
     * Bulk put method for multiple {@code short} values.
     *
     * This method writes multiple {@code short} values to consecutive addresses,
     * beginning at the given offset, from an array.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the first value will be written.
     * @param dst the array to get values from.
     * @param idx the start index in the {@code dst} array to begin reading values.
     * @param len the number of values to be written.
     */
    abstract public void put(long offset, short[] dst, int idx, int len);

    /**
     * Bulk get method for multiple {@code int} values.
     *
     * This method reads multiple {@code int} values from consecutive addresses,
     * beginning at the given offset, and stores them in an array.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the first value will be read.
     * @param dst The array into which values are to be stored.
     * @param idx the start index in the {@code dst} array to begin storing the values.
     * @param len the number of values to be read.
     */
    abstract public void get(long offset, int[] dst, int idx, int len);

    /**
     * Bulk put method for multiple {@code int} values.
     *
     * This method writes multiple {@code int} values to consecutive addresses,
     * beginning at the given offset, from an array.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the first value will be written.
     * @param dst the array to get values from.
     * @param idx the start index in the {@code dst} array to begin reading values.
     * @param len the number of values to be written.
     */
    abstract public void put(long offset, int[] dst, int idx, int len);

    /**
     * Bulk get method for multiple {@code long} values.
     *
     * This method reads multiple {@code long} values from consecutive addresses,
     * beginning at the given offset, and stores them in an array.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the first value will be read.
     * @param dst The array into which values are to be stored.
     * @param idx the start index in the {@code dst} array to begin storing the values.
     * @param len the number of values to be read.
     */
    abstract public void get(long offset, long[] dst, int idx, int len);

    /**
     * Bulk put method for multiple {@code long} values.
     *
     * This method writes multiple {@code long} values to consecutive addresses,
     * beginning at the given offset, from an array.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the first value will be written.
     * @param dst the array to get values from.
     * @param idx the start index in the {@code dst} array to begin reading values.
     * @param len the number of values to be written.
     */
    abstract public void put(long offset, long[] dst, int idx, int len);

    /**
     * Bulk get method for multiple {@code float} values.
     *
     * This method reads multiple {@code float} values from consecutive addresses,
     * beginning at the given offset, and stores them in an array.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the first value will be read.
     * @param dst The array into which values are to be stored.
     * @param idx the start index in the {@code dst} array to begin storing the values.
     * @param len the number of values to be read.
     */
    abstract public void get(long offset, float[] dst, int idx, int len);

    /**
     * Bulk put method for multiple {@code float} values.
     *
     * This method writes multiple {@code float} values to consecutive addresses,
     * beginning at the given offset, from an array.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the first value will be written.
     * @param dst the array to get values from.
     * @param idx the start index in the {@code dst} array to begin reading values.
     * @param len the number of values to be written.
     */
    abstract public void put(long offset, float[] dst, int idx, int len);

    /**
     * Bulk get method for multiple {@code double} values.
     *
     * This method reads multiple {@code double} values from consecutive addresses,
     * beginning at the given offset, and stores them in an array.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the first value will be read.
     * @param dst The array into which values are to be stored.
     * @param idx the start index in the {@code dst} array to begin storing the values.
     * @param len the number of values to be read.
     */
    abstract public void get(long offset, double[] dst, int idx, int len);

    /**
     * Bulk put method for multiple {@code double} values.
     *
     * This method writes multiple {@code double} values to consecutive addresses,
     * beginning at the given offset, from an array.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the first value will be written.
     * @param dst the array to get values from.
     * @param idx the start index in the {@code dst} array to begin reading values.
     * @param len the number of values to be written.
     */
    abstract public void put(long offset, double[] dst, int idx, int len);

    /**
     * Reads an {@code Pointer} value at the given offset.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the {@code Pointer} value read from memory.
     */
    abstract public Pointer getPointer(long offset);

    /**
     * Reads an {@code Pointer} value at the given offset.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @param size the maximum size of the memory location the returned {@code Pointer} represents.
     * @return the {@code Pointer} value read from memory.
     */
    abstract public Pointer getPointer(long offset, long size);

    /**
     * Writes a {@code Pointer} value at the given offset.
     *
     * @param offset The offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param value the {@code Pointer} value to be written to memory.
     */
    abstract public void putPointer(long offset, Pointer value);

    /**
     * Reads an {@code String} value at the given offset.
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @return the {@code String} value read from memory.
     */
    abstract public String getString(long offset);

    /**
     * Reads a {@code String} value at the given offset, using a specific {@code Charset}
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the value will be read.
     * @param maxLength the maximum size of memory to search for a NUL byte.
     * @param cs the {@code Charset} to use to decode the string.
     * @return the {@code String} value read from memory.
     */
    abstract public String getString(long offset, int maxLength, Charset cs);

    /**
     * Writes a {@code String} value at the given offset, using a specific {@code Charset}
     *
     * @param offset the offset from the start of the memory this {@code Pointer} represents at which the value will be written.
     * @param maxLength the maximum size of memory to use to store the string.
     * @param cs the {@code Charset} to use to decode the string.
     */
    abstract public void putString(long offset, String string, int maxLength, Charset cs);

    /**
     * Creates a new {@code Pointer} representing a sub-region of the memory
     * referred to by this {@code Pointer}.
     *
     * @param offset the offset from the start of the memory this {@code Pointer}
     * represents at which the new {@code Pointer} will start.
     * @return a {@code Pointer} instance representing the new sub-region.
     */
    abstract public Pointer slice(long offset);

    /**
     * Creates a new {@code Pointer} representing a sub-region of the memory
     * referred to by this {@code Pointer}.
     *
     * @param offset the offset from the start of the memory this {@code Pointer}
     * represents at which the new {@code Pointer} will start.
     * @param size the maximum size of the memory sub-region.
     *
     * @return a {@code Pointer} instance representing the new sub-region.
     */
    abstract public Pointer slice(long offset, long size);

    /**
     * Bulk data transfer from one memory location to another.
     *
     * @param offset the offset from the start of the memory location this {@code Pointer} represents to begin copying from.
     * @param dst the destination memory location to transfer data to.
     * @param dstOffset the offset from the start of the memory location the destination {@code Pointer} represents to begin copying to.
     * @param count the number of bytes to transfer.
     */
    abstract public void transferTo(long offset, Pointer dst, long dstOffset, long count);

    /**
     * Bulk data transfer from one memory location to another.
     *
     * @param offset the offset from the start of the memory location this {@code Pointer} represents to begin copying to.
     * @param src the destination memory location to transfer data from.
     * @param srcOffset the offset from the start of the memory location the destination {@code Pointer} represents to begin copying from.
     * @param count the number of bytes to transfer.
     */
    abstract public void transferFrom(long offset, Pointer src, long srcOffset, long count);

    /**
     * Checks that the memory region is within the bounds of this memory object
     *
     * @param offset the starting point within this memory region.
     * @param length the length of the memory region in bytes
     * @throws java.lang.IndexOutOfBoundsException
     */
    abstract public void checkBounds(long offset, long length);

    /**
     * Sets the value of each byte in the memory area represented by this {@code Pointer}.
     * to a specified value.
     *
     * @param offset the offset from the start of the memory location this {@code Pointer} represents to begin writing to.
     * @param size the number of bytes to set to the value.
     * @param value the value to set each byte to.
     */
    abstract public void setMemory(long offset, long size, byte value);

    /**
     * Returns the location of a byte value within the memory area represented by this {@code Pointer}.
     *
     * @param offset the offset from the start of the memory location this {@code Pointer} represents to begin searching.
     * @param value the {@code byte} value to locate.
     * @return the offset from the start of the search area (i.e. relative to the offset parameter), or -1 if not found.
     */
    abstract public int indexOf(long offset, byte value);

    /**
     * Returns the location of a byte value within the memory area represented by this {@code Pointer}.
     *
     * @param offset the offset from the start of the memory location this {@code Pointer} represents to begin searching.
     * @param value the {@code byte} value to locate.
     * @param maxlen the maximum number of bytes to search for the desired value.
     * @return the offset from the start of the search area (i.e. relative to the offset parameter), or -1 if not found.
     */
    abstract public int indexOf(long offset, byte value, int maxlen);
}
