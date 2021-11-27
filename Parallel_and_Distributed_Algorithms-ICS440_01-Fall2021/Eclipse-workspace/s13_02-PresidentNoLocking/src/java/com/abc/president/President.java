package com.abc.president;

import java.util.*;
import java.util.concurrent.atomic.*;

public class President {
    private volatile Core core;
    private final AtomicBoolean atomicBoolean;

    public President() {
        core = new Core(0, null, null);
        atomicBoolean = new AtomicBoolean(false);
    }

    /** Returns a read-only view of the data */
    public View getView() {
        return new View(core);
    }

    public MutablePresident edit() {
        return new MutablePresident(core);
    }

    private View commitInternal(MutablePresident mp) throws ConcurrentModificationException {
        Core newCore = new Core(mp.revision + 1, mp.firstName, mp.lastName);
        while (!atomicBoolean.compareAndSet(false, true)); // spin (very, very short!)
        try {
            if (core.revision != (newCore.revision - 1)) throw new ConcurrentModificationException();
            core = newCore;
        } finally {
            atomicBoolean.set(false);
        }
        return new View(newCore);
    }

    /** Instances are immutable */
    public static final class View {
        private final Core core;

        private View(Core core) {
            this.core = core;
        }

        public String getFirstName() {
            return core.firstName;
        }

        public String getLastName() {
            return core.lastName;
        }

        public String getFullName() {
            return core.firstName + " " + core.lastName;
        }
    }  // type View

    /** Instances are immutable */
    private static final class Core {
        public final long revision;
        public final String firstName;
        public final String lastName;

        public Core(long revision, String firstName, String lastName) {
            this.revision = revision;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }  // type Core

    public class MutablePresident {
        private final long revision;
        private String firstName;
        private String lastName;

        private MutablePresident(Core core) {
            revision = core.revision;
            firstName = core.firstName;
            lastName = core.lastName;
        }

        public View commit() throws ConcurrentModificationException {
            return commitInternal(this);
        }

        public String getFirstName() {
            return firstName;
        }

        public MutablePresident setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public String getLastName() {
            return lastName;
        }

        public MutablePresident setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
    }  // type MutablePresident
}
