package com.sample.githubapp.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Item(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("description") val description: String,
    @SerializedName("forks") val forks: Int,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("forks_url") val forksUrl: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("pushed_at") val pushedAt: String,
    @SerializedName("id") val id: Int,
    @SerializedName("labels_url") val labelsUrl: String,
    @SerializedName("language") val language: String,
    @SerializedName("name") val name: String,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("private") val `private`: Boolean,
    @SerializedName("releases_url") val releasesUrl: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("stargazers_url") val stargazersUrl: String,
    @SerializedName("topics") val topics: List<String>,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("url") val url: String,
    @SerializedName("watchers") val watchers: Int,
    @SerializedName("watchers_count") val watchersCount: Int
): Parcelable