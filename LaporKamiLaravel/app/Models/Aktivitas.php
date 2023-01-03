<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Aktivitas extends Model
{
    use HasFactory;
    protected $table = "aktifitas";
    protected $primaryKey = "id";
    protected $fillable = ["nik","nama","aktivitas","statusCode"];
    public $timestamps = false;
}
