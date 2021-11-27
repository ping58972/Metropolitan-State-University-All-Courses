package com.abc.string;

/**
 *
 *
 * Instances are immutable.
 */
public final class DeluxeString {
    private final char[] characters;
    private final int fixedHash;

    public DeluxeString(char[] characters) {
        this.characters = characters.clone();

        int charCount = Math.min(characters.length, 4);
        int hash = 0xfeedface;
        for (int i = 0; i < charCount; i++) {
            hash = (hash << 4) ^ characters[i];
        }
        fixedHash = hash;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        } else if ( obj == null || !getClass().equals(obj.getClass()) ) {
            return false;
        }

        DeluxeString other = (DeluxeString) obj;

        if (fixedHash != other.fixedHash) return false;

        if (characters.length != other.characters.length) return false;

        for (int i = 0; i < characters.length; i++) {
            if (characters[i] != other.characters[i]) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return fixedHash;
//        int charCount = Math.min(characters.length, 4);
//        int hash = 0xfeedface;
//        for (int i = 0; i < charCount; i++) {
//            hash = (hash << 4) ^ characters[i];
//        }
//        return hash;
    }

    @Override
    public String toString() {
        return new String(characters);
    }
}
