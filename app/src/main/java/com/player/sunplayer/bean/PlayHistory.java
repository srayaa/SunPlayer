package com.player.sunplayer.bean;

import android.content.Context;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PlayHistory {
    private static final String textName = "playHistory.txt";
    Context context;

    public PlayHistory(Context context) {
        this.context = context;
    }

    public List<History> save(History history) {
        List<History> all;
        Exception e;
        Throwable th;
        ObjectOutputStream objectOutputStream = null;
        try {
            all = getAll();
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(this.context.openFileOutput(textName, 0));
                int i = 0;
                while (i < all.size()) {
                    try {
                        if (((History) all.get(i)).getUrl().equals(history.getUrl())) {
                            all.remove(i);
                            break;
                        }
                        i++;
                    } catch (Exception e2) {
                        e = e2;
                        objectOutputStream = objectOutputStream2;
                        try {
                            e.printStackTrace();
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            return all;
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                            objectOutputStream2 = objectOutputStream;
                            if (objectOutputStream2 != null) {
                                try {
                                    objectOutputStream2.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                        if (objectOutputStream2 != null) {
                            try {
                                objectOutputStream2.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                    }
                }
                if (all.size() == 100) {
                    all.remove(99);
                }
                all.add(0, history);
                objectOutputStream2.writeObject(all);
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                return all;
            }
        } catch (Exception e6) {
            e = e6;
            all = null;
            e.printStackTrace();
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return all;
        }
        return all;
    }

    public List<History> delete(int i) {
        List<History> all;
        Exception e;
        Throwable th;
        ObjectOutputStream objectOutputStream = null;
        try {
            ObjectOutputStream objectOutputStream2 = null;
            all = getAll();
            try {
                objectOutputStream2 = new ObjectOutputStream(this.context.openFileOutput(textName, 0));
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    return all;
                } catch (Throwable th2) {
                    th2.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
            try {
                all.remove(i);
                objectOutputStream2.writeObject(all);
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                objectOutputStream = objectOutputStream2;
                e.printStackTrace();
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                return all;
            } catch (Throwable th3) {
                th3.printStackTrace();
                objectOutputStream = objectOutputStream2;
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            }
        } catch (Exception e6) {
            e = e6;
            all = null;
            e.printStackTrace();
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return all;
        }
        return all;
    }

    public List<History> delete(History history) {
        List<History> all;
        Exception e;
        Throwable th;
        ObjectOutputStream objectOutputStream = null;
        try {
            all = getAll();
            try {
                int i = 0;
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(this.context.openFileOutput(textName, 0));
                while (i < all.size()) {
                    try {
                        if (((History) all.get(i)).getUrl().equals(history.getUrl())) {
                            all.remove(history);
                        }
                        i++;
                    } catch (Exception e2) {
                        e = e2;
                        objectOutputStream = objectOutputStream2;
                        try {
                            e.printStackTrace();
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            return all;
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                            objectOutputStream2 = objectOutputStream;
                            if (objectOutputStream2 != null) {
                                try {
                                    objectOutputStream2.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                        if (objectOutputStream2 != null) {
                            try {
                                objectOutputStream2.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                    }
                }
                objectOutputStream2.writeObject(all);
                if (objectOutputStream2 != null) {
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                return all;
            }
        } catch (Exception e6) {
            e = e6;
            all = null;
            e.printStackTrace();
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return all;
        }
        return all;
    }

    public void deleteAll() {
        try {
            FileOutputStream openFileOutput = this.context.openFileOutput(textName, 0);
            if (openFileOutput != null) {
                try {
                    openFileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public List<History> getAll() {
        List<History> list = null;
        Exception e;
        Throwable th;
        ObjectInputStream objectInputStream = null;
        ObjectInputStream objectInputStream2;
        try {
            objectInputStream2 = new ObjectInputStream(this.context.openFileInput(textName));
            try {
                List<History> list2 = (List) objectInputStream2.readObject();
                if (objectInputStream2 != null) {
                    try {
                        objectInputStream2.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                list = list2;
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (objectInputStream2 != null) {
                        try {
                            objectInputStream2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    return list == null ? list : new ArrayList();
                } catch (Throwable th2) {
                    ObjectInputStream objectInputStream3 = objectInputStream2;
                    th = th2;
                    objectInputStream = objectInputStream3;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e5) {
            e = e5;
            objectInputStream2 = null;
            e.printStackTrace();
            if (objectInputStream2 != null) {
                try {
                    objectInputStream2.close();
                } catch (IOException e42) {
                    e42.printStackTrace();
                }
            }
            if (list == null) {
            }
        } catch (Throwable th3) {
            th3.printStackTrace();
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
            }
        }
        if (list == null) {
        }
        return list;
    }
}
