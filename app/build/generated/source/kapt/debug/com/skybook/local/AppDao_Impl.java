package com.skybook.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDao_Impl implements AppDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

  private final EntityInsertionAdapter<FlightEntity> __insertionAdapterOfFlightEntity;

  private final EntityInsertionAdapter<BookingEntity> __insertionAdapterOfBookingEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBookingStatus;

  public AppDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `users` (`id`,`name`,`email`,`password`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmail());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPassword());
        }
      }
    };
    this.__insertionAdapterOfFlightEntity = new EntityInsertionAdapter<FlightEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `flights` (`id`,`fromCity`,`toCity`,`fromCode`,`toCode`,`departureTime`,`arrivalTime`,`duration`,`price`,`airlineName`,`airlineLogo`,`classType`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FlightEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getFromCity() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFromCity());
        }
        if (entity.getToCity() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getToCity());
        }
        if (entity.getFromCode() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getFromCode());
        }
        if (entity.getToCode() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getToCode());
        }
        if (entity.getDepartureTime() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDepartureTime());
        }
        if (entity.getArrivalTime() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getArrivalTime());
        }
        if (entity.getDuration() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDuration());
        }
        statement.bindDouble(9, entity.getPrice());
        if (entity.getAirlineName() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getAirlineName());
        }
        if (entity.getAirlineLogo() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getAirlineLogo());
        }
        if (entity.getClassType() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getClassType());
        }
      }
    };
    this.__insertionAdapterOfBookingEntity = new EntityInsertionAdapter<BookingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `bookings` (`id`,`userId`,`flightId`,`seatNumber`,`bookingDate`,`status`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookingEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getFlightId());
        if (entity.getSeatNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSeatNumber());
        }
        if (entity.getBookingDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBookingDate());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStatus());
        }
      }
    };
    this.__preparedStmtOfUpdateBookingStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE bookings SET status = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object registerUser(final UserEntity user, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserEntity.insertAndReturnId(user);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertFlights(final List<FlightEntity> flights,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFlightEntity.insert(flights);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object bookTicket(final BookingEntity booking,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfBookingEntity.insertAndReturnId(booking);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBookingStatus(final int bookingId, final String status,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBookingStatus.acquire();
        int _argIndex = 1;
        if (status == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, status);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, bookingId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateBookingStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object login(final String email, final String password,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE email = ? AND password = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (email == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, email);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            _result = new UserEntity(_tmpId,_tmpName,_tmpEmail,_tmpPassword);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getUserProfile(final int userId,
      final Continuation<? super UserEntity> $completion) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPassword;
            if (_cursor.isNull(_cursorIndexOfPassword)) {
              _tmpPassword = null;
            } else {
              _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            }
            _result = new UserEntity(_tmpId,_tmpName,_tmpEmail,_tmpPassword);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object searchFlights(final String from, final String to,
      final Continuation<? super List<FlightEntity>> $completion) {
    final String _sql = "SELECT * FROM flights WHERE fromCity LIKE '%' || ? || '%' AND toCity LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (from == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, from);
    }
    _argIndex = 2;
    if (to == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, to);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FlightEntity>>() {
      @Override
      @NonNull
      public List<FlightEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromCity = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCity");
          final int _cursorIndexOfToCity = CursorUtil.getColumnIndexOrThrow(_cursor, "toCity");
          final int _cursorIndexOfFromCode = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCode");
          final int _cursorIndexOfToCode = CursorUtil.getColumnIndexOrThrow(_cursor, "toCode");
          final int _cursorIndexOfDepartureTime = CursorUtil.getColumnIndexOrThrow(_cursor, "departureTime");
          final int _cursorIndexOfArrivalTime = CursorUtil.getColumnIndexOrThrow(_cursor, "arrivalTime");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfAirlineName = CursorUtil.getColumnIndexOrThrow(_cursor, "airlineName");
          final int _cursorIndexOfAirlineLogo = CursorUtil.getColumnIndexOrThrow(_cursor, "airlineLogo");
          final int _cursorIndexOfClassType = CursorUtil.getColumnIndexOrThrow(_cursor, "classType");
          final List<FlightEntity> _result = new ArrayList<FlightEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FlightEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFromCity;
            if (_cursor.isNull(_cursorIndexOfFromCity)) {
              _tmpFromCity = null;
            } else {
              _tmpFromCity = _cursor.getString(_cursorIndexOfFromCity);
            }
            final String _tmpToCity;
            if (_cursor.isNull(_cursorIndexOfToCity)) {
              _tmpToCity = null;
            } else {
              _tmpToCity = _cursor.getString(_cursorIndexOfToCity);
            }
            final String _tmpFromCode;
            if (_cursor.isNull(_cursorIndexOfFromCode)) {
              _tmpFromCode = null;
            } else {
              _tmpFromCode = _cursor.getString(_cursorIndexOfFromCode);
            }
            final String _tmpToCode;
            if (_cursor.isNull(_cursorIndexOfToCode)) {
              _tmpToCode = null;
            } else {
              _tmpToCode = _cursor.getString(_cursorIndexOfToCode);
            }
            final String _tmpDepartureTime;
            if (_cursor.isNull(_cursorIndexOfDepartureTime)) {
              _tmpDepartureTime = null;
            } else {
              _tmpDepartureTime = _cursor.getString(_cursorIndexOfDepartureTime);
            }
            final String _tmpArrivalTime;
            if (_cursor.isNull(_cursorIndexOfArrivalTime)) {
              _tmpArrivalTime = null;
            } else {
              _tmpArrivalTime = _cursor.getString(_cursorIndexOfArrivalTime);
            }
            final String _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getString(_cursorIndexOfDuration);
            }
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final String _tmpAirlineName;
            if (_cursor.isNull(_cursorIndexOfAirlineName)) {
              _tmpAirlineName = null;
            } else {
              _tmpAirlineName = _cursor.getString(_cursorIndexOfAirlineName);
            }
            final String _tmpAirlineLogo;
            if (_cursor.isNull(_cursorIndexOfAirlineLogo)) {
              _tmpAirlineLogo = null;
            } else {
              _tmpAirlineLogo = _cursor.getString(_cursorIndexOfAirlineLogo);
            }
            final String _tmpClassType;
            if (_cursor.isNull(_cursorIndexOfClassType)) {
              _tmpClassType = null;
            } else {
              _tmpClassType = _cursor.getString(_cursorIndexOfClassType);
            }
            _item = new FlightEntity(_tmpId,_tmpFromCity,_tmpToCity,_tmpFromCode,_tmpToCode,_tmpDepartureTime,_tmpArrivalTime,_tmpDuration,_tmpPrice,_tmpAirlineName,_tmpAirlineLogo,_tmpClassType);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getFlightCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM flights";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getUserBookings(final int userId,
      final Continuation<? super List<BookingWithFlight>> $completion) {
    final String _sql = "SELECT * FROM bookings WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, true, _cancellationSignal, new Callable<List<BookingWithFlight>>() {
      @Override
      @NonNull
      public List<BookingWithFlight> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
            final int _cursorIndexOfFlightId = CursorUtil.getColumnIndexOrThrow(_cursor, "flightId");
            final int _cursorIndexOfSeatNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "seatNumber");
            final int _cursorIndexOfBookingDate = CursorUtil.getColumnIndexOrThrow(_cursor, "bookingDate");
            final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
            final LongSparseArray<FlightEntity> _collectionFlight = new LongSparseArray<FlightEntity>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfFlightId);
              _collectionFlight.put(_tmpKey, null);
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipflightsAscomSkybookLocalFlightEntity(_collectionFlight);
            final List<BookingWithFlight> _result = new ArrayList<BookingWithFlight>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final BookingWithFlight _item;
              final BookingEntity _tmpBooking;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final int _tmpUserId;
              _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
              final int _tmpFlightId;
              _tmpFlightId = _cursor.getInt(_cursorIndexOfFlightId);
              final String _tmpSeatNumber;
              if (_cursor.isNull(_cursorIndexOfSeatNumber)) {
                _tmpSeatNumber = null;
              } else {
                _tmpSeatNumber = _cursor.getString(_cursorIndexOfSeatNumber);
              }
              final String _tmpBookingDate;
              if (_cursor.isNull(_cursorIndexOfBookingDate)) {
                _tmpBookingDate = null;
              } else {
                _tmpBookingDate = _cursor.getString(_cursorIndexOfBookingDate);
              }
              final String _tmpStatus;
              if (_cursor.isNull(_cursorIndexOfStatus)) {
                _tmpStatus = null;
              } else {
                _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
              }
              _tmpBooking = new BookingEntity(_tmpId,_tmpUserId,_tmpFlightId,_tmpSeatNumber,_tmpBookingDate,_tmpStatus);
              final FlightEntity _tmpFlight;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfFlightId);
              _tmpFlight = _collectionFlight.get(_tmpKey_1);
              _item = new BookingWithFlight(_tmpBooking,_tmpFlight);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
            _statement.release();
          }
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipflightsAscomSkybookLocalFlightEntity(
      @NonNull final LongSparseArray<FlightEntity> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, false, (map) -> {
        __fetchRelationshipflightsAscomSkybookLocalFlightEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`fromCity`,`toCity`,`fromCode`,`toCode`,`departureTime`,`arrivalTime`,`duration`,`price`,`airlineName`,`airlineLogo`,`classType` FROM `flights` WHERE `id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfFromCity = 1;
      final int _cursorIndexOfToCity = 2;
      final int _cursorIndexOfFromCode = 3;
      final int _cursorIndexOfToCode = 4;
      final int _cursorIndexOfDepartureTime = 5;
      final int _cursorIndexOfArrivalTime = 6;
      final int _cursorIndexOfDuration = 7;
      final int _cursorIndexOfPrice = 8;
      final int _cursorIndexOfAirlineName = 9;
      final int _cursorIndexOfAirlineLogo = 10;
      final int _cursorIndexOfClassType = 11;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final FlightEntity _item_1;
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          final String _tmpFromCity;
          if (_cursor.isNull(_cursorIndexOfFromCity)) {
            _tmpFromCity = null;
          } else {
            _tmpFromCity = _cursor.getString(_cursorIndexOfFromCity);
          }
          final String _tmpToCity;
          if (_cursor.isNull(_cursorIndexOfToCity)) {
            _tmpToCity = null;
          } else {
            _tmpToCity = _cursor.getString(_cursorIndexOfToCity);
          }
          final String _tmpFromCode;
          if (_cursor.isNull(_cursorIndexOfFromCode)) {
            _tmpFromCode = null;
          } else {
            _tmpFromCode = _cursor.getString(_cursorIndexOfFromCode);
          }
          final String _tmpToCode;
          if (_cursor.isNull(_cursorIndexOfToCode)) {
            _tmpToCode = null;
          } else {
            _tmpToCode = _cursor.getString(_cursorIndexOfToCode);
          }
          final String _tmpDepartureTime;
          if (_cursor.isNull(_cursorIndexOfDepartureTime)) {
            _tmpDepartureTime = null;
          } else {
            _tmpDepartureTime = _cursor.getString(_cursorIndexOfDepartureTime);
          }
          final String _tmpArrivalTime;
          if (_cursor.isNull(_cursorIndexOfArrivalTime)) {
            _tmpArrivalTime = null;
          } else {
            _tmpArrivalTime = _cursor.getString(_cursorIndexOfArrivalTime);
          }
          final String _tmpDuration;
          if (_cursor.isNull(_cursorIndexOfDuration)) {
            _tmpDuration = null;
          } else {
            _tmpDuration = _cursor.getString(_cursorIndexOfDuration);
          }
          final double _tmpPrice;
          _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
          final String _tmpAirlineName;
          if (_cursor.isNull(_cursorIndexOfAirlineName)) {
            _tmpAirlineName = null;
          } else {
            _tmpAirlineName = _cursor.getString(_cursorIndexOfAirlineName);
          }
          final String _tmpAirlineLogo;
          if (_cursor.isNull(_cursorIndexOfAirlineLogo)) {
            _tmpAirlineLogo = null;
          } else {
            _tmpAirlineLogo = _cursor.getString(_cursorIndexOfAirlineLogo);
          }
          final String _tmpClassType;
          if (_cursor.isNull(_cursorIndexOfClassType)) {
            _tmpClassType = null;
          } else {
            _tmpClassType = _cursor.getString(_cursorIndexOfClassType);
          }
          _item_1 = new FlightEntity(_tmpId,_tmpFromCity,_tmpToCity,_tmpFromCode,_tmpToCode,_tmpDepartureTime,_tmpArrivalTime,_tmpDuration,_tmpPrice,_tmpAirlineName,_tmpAirlineLogo,_tmpClassType);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
